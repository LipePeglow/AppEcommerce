package com.mobilesales.ecommerce.repository

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.storage.ktx.storage
import com.mobilesales.ecommerce.model.*

class ProductsRepository (application: Application) {


    private val firestore = FirebaseFirestore.getInstance()
    private val storage  = Firebase.storage(Firebase.app)
    private val glide = Glide.with(application)

    fun allCategories(): LiveData<List<ProductCategory>> {

        val liveData = MutableLiveData<List<ProductCategory>>()

        firestore.collection("product_categories").addSnapshotListener { snap, error ->

            if (error != null) return@addSnapshotListener

            val list = mutableListOf<ProductCategory>()
            snap?.forEach {
                val productCategory = it.toObject(ProductCategory::class.java)
                productCategory.id = it.id
                list.add(productCategory)
            }
            liveData.value = list
        }
        return liveData
    }


    fun featuredCategories(): LiveData<List<ProductCategory>> {

        val liveData = MutableLiveData<List<ProductCategory>>()

        firestore.collection("product_categories")
            .whereEqualTo("featured", true)
            .addSnapshotListener { snap, error ->

                if (error != null) return@addSnapshotListener

                val list = mutableListOf<ProductCategory>()
                snap?.forEach {
                    val productCategory = it.toObject(ProductCategory::class.java)
                    productCategory.id = it.id
                    list.add(productCategory)
                }
                liveData.value = list
            }
        return liveData
    }


    fun featuredProducts(): LiveData<List<Product>> {

        val liveData = MutableLiveData<List<Product>>()

        firestore.collection("products")
            .whereEqualTo("featured", true)
            .addSnapshotListener { snap, error ->

                if (error != null) return@addSnapshotListener

                val list = mutableListOf<Product>()
                snap?.forEach {
                    val product = it.toObject(Product::class.java)
                    product.id = it.id
                    list.add(product)
                }
                liveData.value = list
            }
        return liveData
    }

    fun loadProductsByCategory(categoryId: String): LiveData<List<Product>> {

        val liveData = MutableLiveData<List<Product>>()
        firestore.collection("product_categories")
            .document(categoryId)
            .get(com.google.firebase.firestore.Source.CACHE)
            .addOnSuccessListener { category ->
                val list = mutableListOf<Product>()

                if (category.get("poducts") != null) {
                    val productsRef = category.get("products") as List<DocumentReference>
                    productsRef.forEach { doc ->
                        firestore.document(doc.path).get().addOnSuccessListener { doc ->
                            val product = doc.toObject(Product::class.java)

                            product?.id = doc.id
                            list.add(product!!)
                            liveData.value = list
                        }
                    }
                }
            }
        return liveData
    }


    fun loadProductById(productId: String): MutableLiveData<ProductVariants> {
        val productVariants = ProductVariants()
        val liveData = MutableLiveData<ProductVariants>(productVariants)

        val productRef = firestore.collection("products").document(productId)

        productRef.get().addOnSuccessListener { snap ->
            productVariants.apply {
                product = snap?.toObject(Product::class.java)!!
                product.id = snap.id
                liveData.value = this
            }
        }
        productRef.collection("colors").get().addOnSuccessListener { colors ->
            productVariants.colors = colors.toObjects(ProductColor::class.java)
            liveData.value = productVariants
        }
        productRef.collection("sizes").get().addOnSuccessListener { sizes ->
            productVariants.sizes = sizes.toObjects(ProductSize::class.java)
            liveData.value = productVariants
        }
        productRef.collection("images").get().addOnSuccessListener { images ->
            productVariants.images = images.toObjects(ProductImage::class.java)
            liveData.value = productVariants

        }
        return liveData
    }

    fun loadThumbnail(product : Product, imageView: ImageView){
        storage.reference.child("products/${product.id}/${product.thumbnail}")
            .downloadUrl.addOnSuccessListener {
            glide.load(it)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(com.mobilesales.ecommerce.R.drawable.placeholder)
                .placeholder(com.mobilesales.ecommerce.R.drawable.placeholder)
                .into(imageView)
        }
    }

    fun loadImagesThumbnail(product: Product, path: String, imageView: ImageView){
        storage.reference.child("products/${product.id}/$path")
            .downloadUrl.addOnSuccessListener {
                glide.load(it)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(com.mobilesales.ecommerce.R.drawable.placeholder)
                    .placeholder(com.mobilesales.ecommerce.R.drawable.placeholder)
                    .into(imageView)
            }
    }

}