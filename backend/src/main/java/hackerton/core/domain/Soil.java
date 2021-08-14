package hackerton.core.domain;


public class Soil {

    private String PNU_Code;    // 지번 코드
    private String Any_Year;       // 시료채취년도
    private String Exam_Day;       // 토양검정일
    private String Exam_Type;      // 경지구분코드
    private String Pnu_Nm;      // 대상지 지번주소

    private double ACID;        // 산도 (pH)
    private double VLDPHA;      // 유효인산 (mg/kg)
    private double VLDSIA;      // 유효규산 (mg/kg)
    private double OM;          // 유기물 (g/kg)
    private double POSIFERT_MG; // 마그네슘 (cmol+/kg)
    private double POSIFERT_K;  // 칼륨 (cmol+/kg)
    private double POSIFERT_CA; // 칼슘 (cmol+/kg)
    private double SELC;        // 전기전도도 (dS/m)


    public String getPNU_Code() {
        return PNU_Code;
    }

    public void setPNU_Code(String PNU_Code) {
        this.PNU_Code = PNU_Code;
    }

    public String getAny_Year() {
        return Any_Year;
    }

    public void setAny_Year(String any_Year) {
        Any_Year = any_Year;
    }

    public String getExam_Day() {
        return Exam_Day;
    }

    public void setExam_Day(String exam_Day) {
        Exam_Day = exam_Day;
    }

    public String getExam_Type() {
        return Exam_Type;
    }

    public void setExam_Type(String exam_Type) {
        Exam_Type = exam_Type;
    }

    public String getPnu_Nm() {
        return Pnu_Nm;
    }

    public void setPnu_Nm(String pnu_Nm) {
        Pnu_Nm = pnu_Nm;
    }

    public double getACID() {
        return ACID;
    }

    public void setACID(double ACID) {
        this.ACID = ACID;
    }

    public double getVLDPHA() {
        return VLDPHA;
    }

    public void setVLDPHA(double VLDPHA) {
        this.VLDPHA = VLDPHA;
    }

    public double getVLDSIA() {
        return VLDSIA;
    }

    public void setVLDSIA(double VLDSIA) {
        this.VLDSIA = VLDSIA;
    }

    public double getOM() {
        return OM;
    }

    public void setOM(double OM) {
        this.OM = OM;
    }

    public double getPOSIFERT_MG() {
        return POSIFERT_MG;
    }

    public void setPOSIFERT_MG(double POSIFERT_MG) {
        this.POSIFERT_MG = POSIFERT_MG;
    }

    public double getPOSIFERT_K() {
        return POSIFERT_K;
    }

    public void setPOSIFERT_K(double POSIFERT_K) {
        this.POSIFERT_K = POSIFERT_K;
    }

    public double getPOSIFERT_CA() {
        return POSIFERT_CA;
    }

    public void setPOSIFERT_CA(double POSIFERT_CA) {
        this.POSIFERT_CA = POSIFERT_CA;
    }

    public double getSELC() {
        return SELC;
    }

    public void setSELC(double SELC) {
        this.SELC = SELC;
    }



}
