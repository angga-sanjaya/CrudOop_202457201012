package Kelas;

public class SesiClass {

    private static String NamaPengguna;
    private static String NamaLengkap;
    private static String Email;
    private static String Status;

    public static String getUserName() {
        return NamaPengguna;
    }

    public static void setUserName(String userName) {
        SesiClass.NamaPengguna = userName;
    }

    public static String getNama() {
        return NamaLengkap;
    }

    public static void setNama(String nama) {
        SesiClass.NamaLengkap = nama;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String email) {
        SesiClass.Email = email;
    }

    public static String getStatus() {
        return Status;
    }

    public static void setStatus(String status) {
        SesiClass.Status = status;
    }
}
