package com.dxctraining.wishlistmgt.wishlist.entities;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wishedlists")
public class WishedItem 
{
		@Id
		@GeneratedValue
		private String id;
		private int customerId;
		private String productId;
		
		public WishedItem() 
		{
			
		}
		
		public WishedItem(int customerId,String productId) 
		{
			this.customerId=customerId;
			this.productId=productId;
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getCustomerId() {
			return customerId;
		}

		public void setCustomerId(Integer customerId) {
			this.customerId = customerId;
		}
		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null||getClass() != obj.getClass())
			{ return false;}
			WishedItem other = (WishedItem) obj;
			return Objects.equals(id, other.id);
		}
}
