package com.itcast.bean.product;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProductType  implements Serializable{
	
	private static final long serialVersionUID = -6826720065899410840L;
	/*���id*/
    private Integer typeid;
    /*�������*/
    private String name;
    /*��ע,����google����ҳ������*/
    private String note;
    /*�Ƿ�ɼ�*/
    private Boolean visible = true;
    /*�����*/
    private Set<ProductType> childtypes = new HashSet<ProductType>();
    /*��������*/
    private ProductType parent;
    
    public ProductType(){
    	
    }
    
    public ProductType(Integer typeid) {
		this.typeid = typeid;
	}

	public ProductType(String name, String note) {
		this.name = name;
		this.note = note;
	}

	@ManyToOne(cascade=CascadeType.REFRESH,optional=true)
    @JoinColumn(name="parentid")
    public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent")
	public Set<ProductType> getChildtypes() {
		return childtypes;
	}

	public void setChildtypes(Set<ProductType> childtypes) {
		this.childtypes = childtypes;
	}

	@Column(length=36,nullable=false,name="name")
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=200,name="note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(nullable=false)
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getTypeid() {
		return typeid;
	}
    
    public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		if (typeid == null) {
			if (other.typeid != null)
				return false;
		} else if (!typeid.equals(other.typeid))
			return false;
		return true;
	}
   
}
