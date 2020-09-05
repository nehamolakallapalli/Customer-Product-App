package com.dxctraining.wishlistmgt.wishlist.dto;

public class WishedItemDto 
{
	 private String id;

	    private int customerId;
	    
	    private String productId;
	    
	    private String customerName;
	    
	    private String productName;

	    public WishedItemDto(){

	    }

	    public WishedItemDto(String id){
	        this.id=id;
	    }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getCusomertName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}
}
