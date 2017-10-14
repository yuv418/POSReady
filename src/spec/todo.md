The POSReady Software Specifications
====================================

* Design a Point-Of-Sale application written in Java that is *completely open-source.
* The point-of-sale-system must be safe, secure, and ready to use in a real-world environment.
* There must be an e-commerce platform to use the same item catalog on an online viewer/shopping cart and the in-store application.
  * Whether or not the ecommerce platform is a separately maintained application (like apache ofbiz) is of unimportance
  * Delete user MUST delete by id. 
  * will not create user if usernames are the same (CreateUser.java)
  * The POSReady program must store <b>pictures</b> of items if entered into the catalog. 
  * (?) migrate settings to sql ? I think so!
  * No! I forgot about items that get priced by how much they weigh. So, what we'll do is if the item is priced by how much it weighs, the unit price goes in the constructor arguement for CreateUser and it will be multiplied accordingly in the sales class (POSRegister)