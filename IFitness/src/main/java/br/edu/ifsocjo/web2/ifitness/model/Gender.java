package br.edu.ifsocjo.web2.ifitness.model;

public enum Gender {

	MASCULINO("Masculino"),
	FEMININO("Feminino"),
	OTHER("Outro"),
	PREFIRO_NAO_DIZER("Prefiro não dizer");
	
	private String description;
	
	private Gender(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
