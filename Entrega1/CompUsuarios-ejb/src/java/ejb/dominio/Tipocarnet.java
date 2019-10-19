/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Prene
 */
@Entity
@Table(name = "TIPOCARNET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocarnet.findAll", query = "SELECT t FROM Tipocarnet t"),
    @NamedQuery(name = "Tipocarnet.findByTipo", query = "SELECT t FROM Tipocarnet t WHERE t.tipo = :tipo")})
public class Tipocarnet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "TIPO")
    private String tipo;
    @JoinTable(name = "LICENCIA", joinColumns = {
        @JoinColumn(name = "TIPO", referencedColumnName = "TIPO")}, inverseJoinColumns = {
        @JoinColumn(name = "NIF", referencedColumnName = "NIF")})
    @ManyToMany
    private List<Cliente> clienteList;

    public Tipocarnet() {
    }

    public Tipocarnet(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return new ArrayList<>(clienteList);
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipo != null ? tipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocarnet)) {
            return false;
        }
        Tipocarnet other = (Tipocarnet) object;
        if ((this.tipo == null && other.tipo != null) || (this.tipo != null && !this.tipo.equals(other.tipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.dominio.Tipocarnet[ tipo=" + tipo + " ]";
    }
    
}
