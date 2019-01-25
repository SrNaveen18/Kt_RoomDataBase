package com.example.naveen.kt_roomdatabase.lamda;

public class MainLamda {


    private FunInterface funInterfaceLamda = () -> System.out.print("JAVA 8");

    void onCreate() {

        funInterfaceLamda.add();

        MemberInner.InnerStatic.a = 10;
        MemberInner.InnerStatic.add();


    }
}
