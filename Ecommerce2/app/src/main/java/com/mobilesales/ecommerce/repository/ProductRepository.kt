package com.mobilesales.ecommerce.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mobilesales.ecommerce.database.AppDataBase
import com.mobilesales.ecommerce.model.*
import io.grpc.InternalChannelz.id
import okio.Source
import org.w3c.dom.Document

class ProductsRepository (application: Application) {

    private val productDao = AppDataBase.getDataBase(application).productDao()

    private val productCategoryDao = AppDataBase.getDataBase(application).productCategoryDao()

    val firestore = FirebaseFirestore.getInstance()


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

}