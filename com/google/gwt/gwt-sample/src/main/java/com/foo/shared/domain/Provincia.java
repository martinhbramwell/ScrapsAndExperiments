package com.foo.shared.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entidad provincia.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 *
 */ 
@Entity
public class Provincia extends EntidadBase implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String nombre;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Provincia)) {
			return false;
		}
		Provincia other = (Provincia) obj;
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		return true;
	}

	public Provincia() {
		this(null, null);
	}

	public Provincia(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Provincia(Provincia pp) {
		super();
		this.id=pp.id;
		this.nombre=pp.nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

	public static ArrayList<Provincia> toArrayList(List<Provincia> p) {
		if (p.isEmpty())
			return new ArrayList<Provincia>();
		ArrayList<Provincia> l = new ArrayList<Provincia>(p.size());

		for (Provincia pp : p)
			l.add(new Provincia(pp));

		return l;

	}
}
