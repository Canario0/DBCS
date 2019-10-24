/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MODELO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m"),
    @NamedQuery(name = "Modelo.findByIdmodelo", query = "SELECT m FROM Modelo m WHERE m.idmodelo = :idmodelo"),
    @NamedQuery(name = "Modelo.findByNombre", query = "SELECT m FROM Modelo m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Modelo.findByFabricante", query = "SELECT m FROM Modelo m WHERE m.fabricante = :fabricante"),
    @NamedQuery(name = "Modelo.findByCostealquiler", query = "SELECT m FROM Modelo m WHERE m.costealquiler = :costealquiler"),
    @NamedQuery(name = "Modelo.findByA\u00f1o", query = "SELECT m FROM Modelo m WHERE m.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "Modelo.findByGps", query = "SELECT m FROM Modelo m WHERE m.gps = :gps")})
public class Modelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "IDMODELO")
    private String idmodelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "FABRICANTE")
    private String fabricante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTEALQUILER")
    private float costealquiler;
    @Basic(optional = false)
    @NotNull
    @Column(name = "A\u00d1O")
    private int año;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GPS")
    private Character gps;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmodelo")
    private List<Vehiculo> vehiculoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmodelo")
    private List<Licenciaspormodelo> licenciaspormodeloList;

    public Modelo() {
    }

    public Modelo(String idmodelo) {
        this.idmodelo = idmodelo;
    }

    public Modelo(String idmodelo, String nombre, String fabricante, float costealquiler, int año, Character gps) {
        this.idmodelo = idmodelo;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.costealquiler = costealquiler;
        this.año = año;
        this.gps = gps;
    }

    public String getIdmodelo() {
        return idmodelo;
    }

    public void setIdmodelo(String idmodelo) {
        this.idmodelo = idmodelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public float getCostealquiler() {
        return costealquiler;
    }

    public void setCostealquiler(float costealquiler) {
        this.costealquiler = costealquiler;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Character getGps() {
        return gps;
    }

    public void setGps(Character gps) {
        this.gps = gps;
    }

    @XmlTransient
    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }

    @XmlTransient
    public List<Licenciaspormodelo> getLicenciaspormodeloList() {
        return licenciaspormodeloList;
    }

    public void setLicenciaspormodeloList(List<Licenciaspormodelo> licenciaspormodeloList) {
        this.licenciaspormodeloList = licenciaspormodeloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmodelo != null ? idmodelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.idmodelo == null && other.idmodelo != null) || (this.idmodelo != null && !this.idmodelo.equals(other.idmodelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.dominio.Modelo[ idmodelo=" + idmodelo + " ]";
    }
    
}
