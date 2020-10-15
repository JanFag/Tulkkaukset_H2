package projekti.tulkkaukset.domain;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tulkkauskieli {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tulkkauskieli")
	private List<Tulkkaus> tulkkaukset;
	
	public Tulkkauskieli(String tulkkauskielennimi) {
		super();
		this.tulkkauskielennimi = tulkkauskielennimi;
	}

	public Tulkkauskieli() {
		
	}
	
	private String tulkkauskielennimi;
	public String getTulkkauskielennimi() {
		return tulkkauskielennimi;
	}

	public void setTulkkauskielennimi(String tulkkauskielennimi) {
		this.tulkkauskielennimi = tulkkauskielennimi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long tulkkauskieliId) {
		this.id = tulkkauskieliId;
	}

	



	public List<Tulkkaus> getTulkkaukset() {
		return tulkkaukset;
	}

	public void setTulkkaukset(List<Tulkkaus> tulkkaukset) {
		this.tulkkaukset = tulkkaukset;
	}

	@Override
	public String toString() {
		return "Tulkkauskieli [tulkkauskieli_id=" + this.id + ", tulkkauskieli=" + this.tulkkauskielennimi + "]";
	}
	
	
	
}
