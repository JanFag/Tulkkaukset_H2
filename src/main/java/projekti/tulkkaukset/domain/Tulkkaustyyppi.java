package projekti.tulkkaukset.domain;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Tulkkaustyyppi {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tulkkaustyyppi")
	private List<Tulkkaus> tulkkaukset;
	
	public Tulkkaustyyppi(String tulkkaustyypinnimi) {
		super();
		this.tulkkaustyypinnimi = tulkkaustyypinnimi;
		
	}

	public Tulkkaustyyppi() {
		
	}

	public void setId(Long tulkkaustyyppiId) {
		this.id = tulkkaustyyppiId;
	}

	private String tulkkaustyypinnimi;
	
	public String getTulkkaustyypinnimi() {
		return tulkkaustyypinnimi;
	}

	public void setTulkkaustyypinnimi(String tulkkaustyypinnimi) {
		this.tulkkaustyypinnimi = tulkkaustyypinnimi;
	}

	public Long getId() {
		return id;
	}





	public List<Tulkkaus> getTulkkaukset() {
		return tulkkaukset;
	}

	public void setTulkkaukset(List<Tulkkaus> tulkkaukset) {
		this.tulkkaukset = tulkkaukset;
	}

	@Override
	public String toString() {
		return "Tulkkaustyyppi [tulkkaustyyppiId=" + this.id + ", tulkkaustyyppi=" + this.tulkkaustyypinnimi + "]";
	}
	
	
	
	
}
