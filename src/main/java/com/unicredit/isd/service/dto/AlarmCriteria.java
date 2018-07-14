package com.unicredit.isd.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Alarm entity. This class is used in AlarmResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /alarms?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AlarmCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter appl;

    private StringFilter bfunc;

    private StringFilter chgj;

    private StringFilter ci;

    private StringFilter desc;

    private StringFilter email;

    private StringFilter fmail;

    private StringFilter fsms;

    private StringFilter ftlg;

    private StringFilter hpsm;

    private StringFilter infrastructure;

    private StringFilter messtext;

    private StringFilter sitname;

    private StringFilter sittype;

    private StringFilter sms;

    private StringFilter tlg;

    private StringFilter url;

    private StringFilter hpsm_override;

    public AlarmCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAppl() {
        return appl;
    }

    public void setAppl(StringFilter appl) {
        this.appl = appl;
    }

    public StringFilter getBfunc() {
        return bfunc;
    }

    public void setBfunc(StringFilter bfunc) {
        this.bfunc = bfunc;
    }

    public StringFilter getChgj() {
        return chgj;
    }

    public void setChgj(StringFilter chgj) {
        this.chgj = chgj;
    }

    public StringFilter getCi() {
        return ci;
    }

    public void setCi(StringFilter ci) {
        this.ci = ci;
    }

    public StringFilter getDesc() {
        return desc;
    }

    public void setDesc(StringFilter desc) {
        this.desc = desc;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getFmail() {
        return fmail;
    }

    public void setFmail(StringFilter fmail) {
        this.fmail = fmail;
    }

    public StringFilter getFsms() {
        return fsms;
    }

    public void setFsms(StringFilter fsms) {
        this.fsms = fsms;
    }

    public StringFilter getFtlg() {
        return ftlg;
    }

    public void setFtlg(StringFilter ftlg) {
        this.ftlg = ftlg;
    }

    public StringFilter getHpsm() {
        return hpsm;
    }

    public void setHpsm(StringFilter hpsm) {
        this.hpsm = hpsm;
    }

    public StringFilter getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(StringFilter infrastructure) {
        this.infrastructure = infrastructure;
    }

    public StringFilter getMesstext() {
        return messtext;
    }

    public void setMesstext(StringFilter messtext) {
        this.messtext = messtext;
    }

    public StringFilter getSitname() {
        return sitname;
    }

    public void setSitname(StringFilter sitname) {
        this.sitname = sitname;
    }

    public StringFilter getSittype() {
        return sittype;
    }

    public void setSittype(StringFilter sittype) {
        this.sittype = sittype;
    }

    public StringFilter getSms() {
        return sms;
    }

    public void setSms(StringFilter sms) {
        this.sms = sms;
    }

    public StringFilter getTlg() {
        return tlg;
    }

    public void setTlg(StringFilter tlg) {
        this.tlg = tlg;
    }

    public StringFilter getUrl() {
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public StringFilter getHpsm_override() {
        return hpsm_override;
    }

    public void setHpsm_override(StringFilter hpsm_override) {
        this.hpsm_override = hpsm_override;
    }

    @Override
    public String toString() {
        return "AlarmCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (appl != null ? "appl=" + appl + ", " : "") +
                (bfunc != null ? "bfunc=" + bfunc + ", " : "") +
                (chgj != null ? "chgj=" + chgj + ", " : "") +
                (ci != null ? "ci=" + ci + ", " : "") +
                (desc != null ? "desc=" + desc + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (fmail != null ? "fmail=" + fmail + ", " : "") +
                (fsms != null ? "fsms=" + fsms + ", " : "") +
                (ftlg != null ? "ftlg=" + ftlg + ", " : "") +
                (hpsm != null ? "hpsm=" + hpsm + ", " : "") +
                (infrastructure != null ? "infrastructure=" + infrastructure + ", " : "") +
                (messtext != null ? "messtext=" + messtext + ", " : "") +
                (sitname != null ? "sitname=" + sitname + ", " : "") +
                (sittype != null ? "sittype=" + sittype + ", " : "") +
                (sms != null ? "sms=" + sms + ", " : "") +
                (tlg != null ? "tlg=" + tlg + ", " : "") +
                (url != null ? "url=" + url + ", " : "") +
                (hpsm_override != null ? "hpsm_override=" + hpsm_override + ", " : "") +
            "}";
    }

}
