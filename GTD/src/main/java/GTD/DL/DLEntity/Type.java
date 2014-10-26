/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.DLEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * Třída reprezentuje obecný typ (vlastnost která nabývá jen urč. konkrétních hodnot)
 * @author steklsim
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType = DiscriminatorType.STRING
)
abstract public class Type
{
	/**
	 * Id typu
	 */
	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * Kód typu
	 */
	@Column(length = 2, nullable = false)
	private String kod;
	
	/**
	 * Název typu
	 */
	@Column(length = 20, nullable = false)
	private String nazev;
	
	/**
	 * Popis typu
	 */
	@Column(length = 200)
	private String popis;

	
	public int getId()
	{
		return id;
	}
	
	public String getKod()
	{
		return kod;
	}

	public void setKod(String kod)
	{
		this.kod = kod;
	}

	public String getNazev()
	{
		return nazev;
	}

	public void setNazev(String nazev)
	{
		this.nazev = nazev;
	}

	public String getPopis()
	{
		return popis;
	}

	public void setPopis(String popis)
	{
		this.popis = popis;
	}

	
	
	
	
}
