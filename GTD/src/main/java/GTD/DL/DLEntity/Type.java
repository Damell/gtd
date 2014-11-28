/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.DLEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Třída reprezentuje obecný typ (vlastnost která nabývá jen urč. konkrétních hodnot)
 * @author steklsim
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType = DiscriminatorType.STRING
)
@Table(
	name = "type",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"type", "kod"})}
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
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getKod()
	{
		return kod;
	}

//	public void setKod(String kod)
//	{
//		this.kod = kod;
//	}

	public String getNazev()
	{
		return nazev;
	}

//	public void setNazev(String nazev)
//	{
//		this.nazev = nazev;
//	}

	public String getPopis()
	{
		return popis;
	}

//	public void setPopis(String popis)
//	{
//		this.popis = popis;
//	}

	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = 43 * hash + this.id;
		hash = 43 * hash + Objects.hashCode(this.kod);
		hash = 43 * hash + Objects.hashCode(this.nazev);
		hash = 43 * hash + Objects.hashCode(this.popis);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Type other = (Type) obj;
		if (this.id != other.id) {
			return false;
		}
		if (!Objects.equals(this.kod, other.kod)) {
			return false;
		}
		if (!Objects.equals(this.nazev, other.nazev)) {
			return false;
		}
		if (!Objects.equals(this.popis, other.popis)) {
			return false;
		}
		return true;
	}

	
	
	
	
}
