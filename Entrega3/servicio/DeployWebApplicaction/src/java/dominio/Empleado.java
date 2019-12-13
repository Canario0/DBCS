/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByNumeroempleado", query = "SELECT e FROM Empleado e WHERE e.numeroempleado = :numeroempleado"),
    @NamedQuery(name = "Empleado.findByFechacontratacion", query = "SELECT e FROM Empleado e WHERE e.fechacontratacion = :fechacontratacion"),
    @NamedQuery(name = "Empleado.findByTipoempleado", query = "SELECT e FROM Empleado e WHERE e.tipoempleado = :tipoempleado")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "NUMEROEMPLEADO")
    private String numeroempleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHACONTRATACION")
    @Temporal(TemporalType.DATE)
    private Date fechacontratacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPOEMPLEADO")
    private Character tipoempleado;
    @JoinColumn(name = "NIF", referencedColumnName = "NIF")
    @ManyToOne(optional = false)
    private Usuario nif;

    public Empleado() {
    }

    public Empleado(String numeroempleado) {
        this.numeroempleado = numeroempleado;
    }

    public Empleado(String numeroempleado, Date fechacontratacion, Character tipoempleado) {
        this.numeroempleado = numeroempleado;
        this.fechacontratacion = fechacontratacion;
        this.tipoempleado = tipoempleado;
    }

    public String getNumeroempleado() {
        return numeroempleado;
    }

    public void setNumeroempleado(String numeroempleado) {
        this.numeroempleado = numeroempleado;
    }

    public Date getFechacontratacion() {
        return fechacontratacion;
    }

    public void setFechacontratacion(Date fechacontratacion) {
        this.fechacontratacion = fechacontratacion;
    }

    public Character getTipoempleado() {
        return tipoempleado;
    }

    public void setTipoempleado(Character tipoempleado) {
        this.tipoempleado = tipoempleado;
    }

    public Usuario getNif() {
        return nif;
    }

    public void setNif(Usuario nif) {
        this.nif = nif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroempleado != null ? numeroempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.numeroempleado == null && other.numeroempleado != null) || (this.numeroempleado != null && !this.numeroempleado.equals(other.numeroempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Empleado[ numeroempleado=" + numeroempleado + " ]";
    }
    
}
