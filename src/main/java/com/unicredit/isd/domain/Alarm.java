package com.unicredit.isd.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Alarm.
 */
@Entity
@Table(name = "alarm")
public class Alarm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "appl", nullable = false)
    private String appl;

    @Column(name = "bfunc")
    private String bfunc;

    @Column(name = "chgj")
    private String chgj;

    @Column(name = "ci")
    private String ci;

    @Column(name = "isd_desc")
    private String desc;

    @Column(name = "email")
    private String email;

    @Column(name = "fmail")
    private String fmail;

    @Column(name = "fsms")
    private String fsms;

    @Column(name = "ftlg")
    private String ftlg;

    @Column(name = "hpsm")
    private String hpsm;

    @Column(name = "infrastructure")
    private String infrastructure;

    @Column(name = "messtext")
    private String messtext;

    @Column(name = "sitname")
    private String sitname;

    @Column(name = "sittype")
    private String sittype;

    @Column(name = "sms")
    private String sms;

    @Column(name = "tlg")
    private String tlg;

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "hpsm_override")
    private String hpsm_override;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppl() {
        return appl;
    }

    public Alarm appl(String appl) {
        this.appl = appl;
        return this;
    }

    public void setAppl(String appl) {
        this.appl = appl;
    }

    public String getBfunc() {
        return bfunc;
    }

    public Alarm bfunc(String bfunc) {
        this.bfunc = bfunc;
        return this;
    }

    public void setBfunc(String bfunc) {
        this.bfunc = bfunc;
    }

    public String getChgj() {
        return chgj;
    }

    public Alarm chgj(String chgj) {
        this.chgj = chgj;
        return this;
    }

    public void setChgj(String chgj) {
        this.chgj = chgj;
    }

    public String getCi() {
        return ci;
    }

    public Alarm ci(String ci) {
        this.ci = ci;
        return this;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getDesc() {
        return desc;
    }

    public Alarm desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public Alarm email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFmail() {
        return fmail;
    }

    public Alarm fmail(String fmail) {
        this.fmail = fmail;
        return this;
    }

    public void setFmail(String fmail) {
        this.fmail = fmail;
    }

    public String getFsms() {
        return fsms;
    }

    public Alarm fsms(String fsms) {
        this.fsms = fsms;
        return this;
    }

    public void setFsms(String fsms) {
        this.fsms = fsms;
    }

    public String getFtlg() {
        return ftlg;
    }

    public Alarm ftlg(String ftlg) {
        this.ftlg = ftlg;
        return this;
    }

    public void setFtlg(String ftlg) {
        this.ftlg = ftlg;
    }

    public String getHpsm() {
        return hpsm;
    }

    public Alarm hpsm(String hpsm) {
        this.hpsm = hpsm;
        return this;
    }

    public void setHpsm(String hpsm) {
        this.hpsm = hpsm;
    }

    public String getInfrastructure() {
        return infrastructure;
    }

    public Alarm infrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
        return this;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    public String getMesstext() {
        return messtext;
    }

    public Alarm messtext(String messtext) {
        this.messtext = messtext;
        return this;
    }

    public void setMesstext(String messtext) {
        this.messtext = messtext;
    }

    public String getSitname() {
        return sitname;
    }

    public Alarm sitname(String sitname) {
        this.sitname = sitname;
        return this;
    }

    public void setSitname(String sitname) {
        this.sitname = sitname;
    }

    public String getSittype() {
        return sittype;
    }

    public Alarm sittype(String sittype) {
        this.sittype = sittype;
        return this;
    }

    public void setSittype(String sittype) {
        this.sittype = sittype;
    }

    public String getSms() {
        return sms;
    }

    public Alarm sms(String sms) {
        this.sms = sms;
        return this;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getTlg() {
        return tlg;
    }

    public Alarm tlg(String tlg) {
        this.tlg = tlg;
        return this;
    }

    public void setTlg(String tlg) {
        this.tlg = tlg;
    }

    public String getUrl() {
        return url;
    }

    public Alarm url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHpsm_override() {
        return hpsm_override;
    }

    public Alarm hpsm_override(String hpsm_override) {
        this.hpsm_override = hpsm_override;
        return this;
    }

    public void setHpsm_override(String hpsm_override) {
        this.hpsm_override = hpsm_override;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alarm alarm = (Alarm) o;
        if (alarm.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alarm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Alarm{" +
            "id=" + getId() +
            ", appl='" + getAppl() + "'" +
            ", bfunc='" + getBfunc() + "'" +
            ", chgj='" + getChgj() + "'" +
            ", ci='" + getCi() + "'" +
            ", desc='" + getDesc() + "'" +
            ", email='" + getEmail() + "'" +
            ", fmail='" + getFmail() + "'" +
            ", fsms='" + getFsms() + "'" +
            ", ftlg='" + getFtlg() + "'" +
            ", hpsm='" + getHpsm() + "'" +
            ", infrastructure='" + getInfrastructure() + "'" +
            ", messtext='" + getMesstext() + "'" +
            ", sitname='" + getSitname() + "'" +
            ", sittype='" + getSittype() + "'" +
            ", sms='" + getSms() + "'" +
            ", tlg='" + getTlg() + "'" +
            ", url='" + getUrl() + "'" +
            ", hpsm_override='" + getHpsm_override() + "'" +
            "}";
    }
}
