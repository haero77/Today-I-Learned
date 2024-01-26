package sample_code.Java.exception_handling;

public class CheckedUncheckedException {
    public static void main(String[] args) {
//        throwCheckedExeception();
        throwUncheckedException();
    }

    static void throwCheckedExeception() throws Exception {
        throw new Exception("Checked Exception");
    }

    static void throwUncheckedException() throws RuntimeException{
        throw new RuntimeException("Unchecked Exception");
    }
}
