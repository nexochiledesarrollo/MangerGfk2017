package cl.nexo.manager.access.general.tools;

import java.util.Date;

public interface AccessDateParceTools {

	public String getParseFecha(int date);

	public int getParseFechaToInt(String date);

	public int getDiferencia2fechas(String fechaIn, String fechaOut);

}
