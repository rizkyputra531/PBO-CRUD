/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rizkyapk.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NANDA NAJWAN NOOR
 */
@Entity
@Table(name = "pramuka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pramuka.findAll", query = "SELECT p FROM Pramuka p")
    , @NamedQuery(name = "Pramuka.findByNta", query = "SELECT p FROM Pramuka p WHERE p.nta = :nta")
    , @NamedQuery(name = "Pramuka.findByNama", query = "SELECT p FROM Pramuka p WHERE p.nama = :nama")
    , @NamedQuery(name = "Pramuka.findByGudep", query = "SELECT p FROM Pramuka p WHERE p.gudep = :gudep")
    , @NamedQuery(name = "Pramuka.findByGolongan", query = "SELECT p FROM Pramuka p WHERE p.golongan = :golongan")})
public class Pramuka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NTA")
    private String nta;
    @Basic(optional = false)
    @Column(name = "Nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "Gudep")
    private String gudep;
    @Basic(optional = false)
    @Column(name = "Golongan")
    private String golongan;

    public Pramuka() {
    }

    public Pramuka(String nta) {
        this.nta = nta;
    }

    public Pramuka(String nta, String nama, String gudep, String golongan) {
        this.nta = nta;
        this.nama = nama;
        this.gudep = gudep;
        this.golongan = golongan;
    }

    public String getNta() {
        return nta;
    }

    public void setNta(String nta) {
        this.nta = nta;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGudep() {
        return gudep;
    }

    public void setGudep(String gudep) {
        this.gudep = gudep;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nta != null ? nta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pramuka)) {
            return false;
        }
        Pramuka other = (Pramuka) object;
        if ((this.nta == null && other.nta != null) || (this.nta != null && !this.nta.equals(other.nta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rizkyapk.models.Pramuka[ nta=" + nta + " ]";
    }
    
}
