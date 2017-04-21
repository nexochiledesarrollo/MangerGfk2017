package cl.nexo.manager.obj.instructivo;

import org.springframework.web.bind.annotation.RequestParam;



public class ObjInstructivo {
	
    int id_operacion;
	boolean rep_ola;
	boolean carta ;
    int loi;
	int incidencia ;
	int tasa ;
	String vista_1;
	String vista_2;
	String grupo_1;	
	String grupo_2;
	String estructura;	
	String glosario;
	String quest_papi1;	
	String quest_papi2;
	String quest_capi1;	
	String quest_capi2;
	String quest_capi3;
    String cuota1;	
	boolean cuota2;
	String cuota3;
	String cuota4;	
	String cuota5;
	boolean cuota6;
	int cuota7;
	String t_campo_desde;	
	String t_campo_hasta;
	String p_temporal;	
	String p_final;
	String desc_obj;	
	String dia_mes;
	String planificacion;
	String plazo_papi_01;	
	String plazo_papi_02;
	String plazo_papi_03;	
	String plazo_papi_04;
	String plazo_capi_01;	
	String plazo_capi_02;
	String plazo_capi_03;
    String remun_01;	
	String remun_02;
	String pack_01;
    String contacto_01;
	String contacto_02;
    String contacto_03;	
	String contacto_04;
	String contacto_05;
	
	
///// Exclusivos CATI
	
	String nombre_guion;	
	String nombre_servidor;
	String paises;
	boolean B2B;
	boolean B2C;
	String Porc_B2B;	
	String Porc_B2C;
	String met_mixto;	
	String coment;
	boolean muestra_ent;	
	String especif;
	boolean rdd;
	boolean muestra_cliente;	
	boolean base_datos;
	boolean otro;
	String especif_otro;
	boolean nombre_contact;
	String porc_nombre;
	boolean permit_recom;
	String especif_recom;
	String n_recom;
	String coment_adic;
	boolean cuota14;
	int cuota15;
	String inst_sup1;	
	String inst_sup2;
		
	
	boolean incent_dinero;
	boolean incent_voucher;
	boolean incent_regalo;
	String incent_especif;
	boolean realiza_cita;
	String coment_incent;
	
	boolean que_papi;
	boolean que_capi;
	boolean que_movil;
	boolean que_mixto;
	String que_otro;
	
	boolean donde_casa;
	boolean donde_central;
	boolean donde_calle;
	boolean donde_tienda;
	String donde_otro;
	
	

	public int getId_operacion() {
		return id_operacion;
	}

	public void setId_operacion(int id_operacion) {
		this.id_operacion = id_operacion;
	}
	
	public boolean isRep_ola() {
		return rep_ola;
	}
	
	public void setRep_ola(boolean rep_ola) {
		this.rep_ola = rep_ola;
	}
	
	public boolean isCarta() {
		return carta;
	}
	
	public void setCarta(boolean carta) {
		this.carta = carta;
	}
	
	public int getLoi() {
		return loi;
	}
	
	public void setLoi(int loi) {
		this.loi = loi;
	}
	
	public int getIncidencia() {
		return incidencia;
	}
	
	public void setIncidencia(int incidencia) {
		this.incidencia = incidencia;
	}
	
	public int getTasa() {
		return tasa;
	}
	
	public void setTasa(int tasa) {
		this.tasa = tasa;
	}

	public String getVista_1() {
		return vista_1;
	}

	public void setVista_1(String vista_1) {
		this.vista_1 = vista_1;
	}

	public String getVista_2() {
		return vista_2;
	}

	public void setVista_2(String vista_2) {
		this.vista_2 = vista_2;
	}

	public String getGrupo_1() {
		return grupo_1;
	}

	public void setGrupo_1(String grupo_1) {
		this.grupo_1 = grupo_1;
	}

	public String getGrupo_2() {
		return grupo_2;
	}

	public void setGrupo_2(String grupo_2) {
		this.grupo_2 = grupo_2;
	}

	public String getEstructura() {
		return estructura;
	}

	public void setEstructura(String estructura) {
		this.estructura = estructura;
	}

	public String getGlosario() {
		return glosario;
	}

	public void setGlosario(String glosario) {
		this.glosario = glosario;
	}

	public String getQuest_papi1() {
		return quest_papi1;
	}

	public void setQuest_papi1(String quest_papi1) {
		this.quest_papi1 = quest_papi1;
	}

	public String getQuest_papi2() {
		return quest_papi2;
	}

	public void setQuest_papi2(String quest_papi2) {
		this.quest_papi2 = quest_papi2;
	}

	public String getQuest_capi1() {
		return quest_capi1;
	}

	public void setQuest_capi1(String quest_capi1) {
		this.quest_capi1 = quest_capi1;
	}

	public String getQuest_capi2() {
		return quest_capi2;
	}

	public void setQuest_capi2(String quest_capi2) {
		this.quest_capi2 = quest_capi2;
	}

	public String getQuest_capi3() {
		return quest_capi3;
	}

	public void setQuest_capi3(String quest_capi3) {
		this.quest_capi3 = quest_capi3;
	}

	public String getCuota1() {
		return cuota1;
	}

	public void setCuota1(String cuota1) {
		this.cuota1 = cuota1;
	}

	public boolean isCuota2() {
		return cuota2;
	}

	public void setCuota2(boolean cuota2) {
		this.cuota2 = cuota2;
	}

	public String getCuota3() {
		return cuota3;
	}

	public void setCuota3(String cuota3) {
		this.cuota3 = cuota3;
	}

	public String getCuota4() {
		return cuota4;
	}

	public void setCuota4(String cuota4) {
		this.cuota4 = cuota4;
	}

	public String getCuota5() {
		return cuota5;
	}

	public void setCuota5(String cuota5) {
		this.cuota5 = cuota5;
	}

	

	public boolean isCuota6() {
		return cuota6;
	}

	public void setCuota6(boolean cuota6) {
		this.cuota6 = cuota6;
	}

	public int getCuota7() {
		return cuota7;
	}

	public void setCuota7(int cuota7) {
		this.cuota7 = cuota7;
	}

	public String getT_campo_desde() {
		return t_campo_desde;
	}

	public void setT_campo_desde(String t_campo_desde) {
		this.t_campo_desde = t_campo_desde;
	}

	public String getT_campo_hasta() {
		return t_campo_hasta;
	}

	public void setT_campo_hasta(String t_campo_hasta) {
		this.t_campo_hasta = t_campo_hasta;
	}

	public String getP_temporal() {
		return p_temporal;
	}

	public void setP_temporal(String p_temporal) {
		this.p_temporal = p_temporal;
	}

	public String getP_final() {
		return p_final;
	}

	public void setP_final(String p_final) {
		this.p_final = p_final;
	}

	public String getDesc_obj() {
		return desc_obj;
	}

	public void setDesc_obj(String desc_obj) {
		this.desc_obj = desc_obj;
	}

	public String getDia_mes() {
		return dia_mes;
	}

	public void setDia_mes(String dia_mes) {
		this.dia_mes = dia_mes;
	}

	public String getPlanificacion() {
		return planificacion;
	}

	public void setPlanificacion(String planificacion) {
		this.planificacion = planificacion;
	}

	public String getPlazo_papi_01() {
		return plazo_papi_01;
	}

	public void setPlazo_papi_01(String plazo_papi_01) {
		this.plazo_papi_01 = plazo_papi_01;
	}

	public String getPlazo_papi_02() {
		return plazo_papi_02;
	}

	public void setPlazo_papi_02(String plazo_papi_02) {
		this.plazo_papi_02 = plazo_papi_02;
	}

	public String getPlazo_papi_03() {
		return plazo_papi_03;
	}

	public void setPlazo_papi_03(String plazo_papi_03) {
		this.plazo_papi_03 = plazo_papi_03;
	}

	public String getPlazo_papi_04() {
		return plazo_papi_04;
	}

	public void setPlazo_papi_04(String plazo_papi_04) {
		this.plazo_papi_04 = plazo_papi_04;
	}

	public String getPlazo_capi_01() {
		return plazo_capi_01;
	}

	public void setPlazo_capi_01(String plazo_capi_01) {
		this.plazo_capi_01 = plazo_capi_01;
	}

	public String getPlazo_capi_02() {
		return plazo_capi_02;
	}

	public void setPlazo_capi_02(String plazo_capi_02) {
		this.plazo_capi_02 = plazo_capi_02;
	}

	public String getPlazo_capi_03() {
		return plazo_capi_03;
	}

	public void setPlazo_capi_03(String plazo_capi_03) {
		this.plazo_capi_03 = plazo_capi_03;
	}

	public String getRemun_01() {
		return remun_01;
	}

	public void setRemun_01(String remun_01) {
		this.remun_01 = remun_01;
	}

	public String getRemun_02() {
		return remun_02;
	}

	public void setRemun_02(String remun_02) {
		this.remun_02 = remun_02;
	}

	public String getPack_01() {
		return pack_01;
	}

	public void setPack_01(String pack_01) {
		this.pack_01 = pack_01;
	}

	public String getContacto_01() {
		return contacto_01;
	}

	public void setContacto_01(String contacto_01) {
		this.contacto_01 = contacto_01;
	}

	public String getContacto_02() {
		return contacto_02;
	}

	public void setContacto_02(String contacto_02) {
		this.contacto_02 = contacto_02;
	}

	public String getContacto_03() {
		return contacto_03;
	}

	public void setContacto_03(String contacto_03) {
		this.contacto_03 = contacto_03;
	}

	public String getContacto_04() {
		return contacto_04;
	}

	public void setContacto_04(String contacto_04) {
		this.contacto_04 = contacto_04;
	}

	public String getContacto_05() {
		return contacto_05;
	}

	public void setContacto_05(String contacto_05) {
		this.contacto_05 = contacto_05;
	}

	public String getNombre_guion() {
		return nombre_guion;
	}

	public void setNombre_guion(String nombre_guion) {
		this.nombre_guion = nombre_guion;
	}

	public String getNombre_servidor() {
		return nombre_servidor;
	}

	public void setNombre_servidor(String nombre_servidor) {
		this.nombre_servidor = nombre_servidor;
	}

	public String getPaises() {
		return paises;
	}

	public void setPaises(String paises) {
		this.paises = paises;
	}

	public boolean isB2B() {
		return B2B;
	}

	public void setB2B(boolean b2b) {
		B2B = b2b;
	}

	public boolean isB2C() {
		return B2C;
	}

	public void setB2C(boolean b2c) {
		B2C = b2c;
	}

	public String getPorc_B2B() {
		return Porc_B2B;
	}

	public void setPorc_B2B(String porc_B2B) {
		Porc_B2B = porc_B2B;
	}

	public String getPorc_B2C() {
		return Porc_B2C;
	}

	public void setPorc_B2C(String porc_B2C) {
		Porc_B2C = porc_B2C;
	}

	public String getMet_mixto() {
		return met_mixto;
	}

	public void setMet_mixto(String met_mixto) {
		this.met_mixto = met_mixto;
	}

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public boolean isMuestra_ent() {
		return muestra_ent;
	}

	public void setMuestra_ent(boolean muestra_ent) {
		this.muestra_ent = muestra_ent;
	}

	public String getEspecif() {
		return especif;
	}

	public void setEspecif(String especif) {
		this.especif = especif;
	}

	public boolean isRdd() {
		return rdd;
	}

	public void setRdd(boolean rdd) {
		this.rdd = rdd;
	}

	public boolean isMuestra_cliente() {
		return muestra_cliente;
	}

	public void setMuestra_cliente(boolean muestra_cliente) {
		this.muestra_cliente = muestra_cliente;
	}

	public boolean isBase_datos() {
		return base_datos;
	}

	public void setBase_datos(boolean base_datos) {
		this.base_datos = base_datos;
	}

	public boolean isOtro() {
		return otro;
	}

	public void setOtro(boolean otro) {
		this.otro = otro;
	}

	public String getEspecif_otro() {
		return especif_otro;
	}

	public void setEspecif_otro(String especif_otro) {
		this.especif_otro = especif_otro;
	}

	public boolean isNombre_contact() {
		return nombre_contact;
	}

	public void setNombre_contact(boolean nombre_contact) {
		this.nombre_contact = nombre_contact;
	}

	public String getPorc_nombre() {
		return porc_nombre;
	}

	public void setPorc_nombre(String porc_nombre) {
		this.porc_nombre = porc_nombre;
	}

	public boolean isPermit_recom() {
		return permit_recom;
	}

	public void setPermit_recom(boolean permit_recom) {
		this.permit_recom = permit_recom;
	}

	public String getEspecif_recom() {
		return especif_recom;
	}

	public void setEspecif_recom(String especif_recom) {
		this.especif_recom = especif_recom;
	}

	public String getN_recom() {
		return n_recom;
	}

	public void setN_recom(String n_recom) {
		this.n_recom = n_recom;
	}

	public String getComent_adic() {
		return coment_adic;
	}

	public void setComent_adic(String coment_adic) {
		this.coment_adic = coment_adic;
	}

	public boolean isCuota14() {
		return cuota14;
	}

	public void setCuota14(boolean cuota14) {
		this.cuota14 = cuota14;
	}

	public int getCuota15() {
		return cuota15;
	}

	public void setCuota15(int cuota15) {
		this.cuota15 = cuota15;
	}

	public String getInst_sup1() {
		return inst_sup1;
	}

	public void setInst_sup1(String inst_sup1) {
		this.inst_sup1 = inst_sup1;
	}

	public String getInst_sup2() {
		return inst_sup2;
	}

	public void setInst_sup2(String inst_sup2) {
		this.inst_sup2 = inst_sup2;
	}

	public boolean isIncent_dinero() {
		return incent_dinero;
	}

	public void setIncent_dinero(boolean incent_dinero) {
		this.incent_dinero = incent_dinero;
	}

	public boolean isIncent_voucher() {
		return incent_voucher;
	}

	public void setIncent_voucher(boolean incent_voucher) {
		this.incent_voucher = incent_voucher;
	}

	public boolean isIncent_regalo() {
		return incent_regalo;
	}

	public void setIncent_regalo(boolean incent_regalo) {
		this.incent_regalo = incent_regalo;
	}

	public String getIncent_especif() {
		return incent_especif;
	}

	public void setIncent_especif(String incent_especif) {
		this.incent_especif = incent_especif;
	}

	public boolean isRealiza_cita() {
		return realiza_cita;
	}

	public void setRealiza_cita(boolean realiza_cita) {
		this.realiza_cita = realiza_cita;
	}

	public String getComent_incent() {
		return coment_incent;
	}

	public void setComent_incent(String coment_incent) {
		this.coment_incent = coment_incent;
	}

	public boolean isQue_papi() {
		return que_papi;
	}

	public void setQue_papi(boolean que_papi) {
		this.que_papi = que_papi;
	}

	public boolean isQue_capi() {
		return que_capi;
	}

	public void setQue_capi(boolean que_capi) {
		this.que_capi = que_capi;
	}

	public boolean isQue_movil() {
		return que_movil;
	}

	public void setQue_movil(boolean que_movil) {
		this.que_movil = que_movil;
	}

	public boolean isQue_mixto() {
		return que_mixto;
	}

	public void setQue_mixto(boolean que_mixto) {
		this.que_mixto = que_mixto;
	}

	public String getQue_otro() {
		return que_otro;
	}

	public void setQue_otro(String que_otro) {
		this.que_otro = que_otro;
	}

	public boolean isDonde_casa() {
		return donde_casa;
	}

	public void setDonde_casa(boolean donde_casa) {
		this.donde_casa = donde_casa;
	}

	public boolean isDonde_central() {
		return donde_central;
	}

	public void setDonde_central(boolean donde_central) {
		this.donde_central = donde_central;
	}

	public boolean isDonde_calle() {
		return donde_calle;
	}

	public void setDonde_calle(boolean donde_calle) {
		this.donde_calle = donde_calle;
	}

	public boolean isDonde_tienda() {
		return donde_tienda;
	}

	public void setDonde_tienda(boolean donde_tienda) {
		this.donde_tienda = donde_tienda;
	}

	public String getDonde_otro() {
		return donde_otro;
	}

	public void setDonde_otro(String donde_otro) {
		this.donde_otro = donde_otro;
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
