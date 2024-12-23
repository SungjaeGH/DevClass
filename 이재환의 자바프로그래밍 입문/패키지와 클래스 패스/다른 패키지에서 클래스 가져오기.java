// 다음은 3개의 파일 Apple.java, Banana.java, Ex02_PackageUse.java의 소스 코드와 폴더 구조를 나타낸 것입니다.
public class Apple {
    public void showName()
    {
        System.out.println("My name is apple.");
    }
}

package com.study;

public class Banana {
    public void showName()
    {
        System.out.println("My name is banana.");
    }
}

public class Ex02_PackageUse
{
    public static void main(String[] args)
    {
        Apple apple = new Apple();
        apple.showName();

        //Banana banana = new Banana();
        com.study.Banana banana = new com.study.Banana();
        banana.showName();
    }
}

/*
* 이에 대한 설명으로 옳지 않은 것을 고르세요. (1)
1. Banana 클래스가 private이나 default로 접근 제한자가 지정되어 있다면 Ex02_PackagerUse.java 파일이 컴파일은 되지만 실행 도중 에러가 발생한다.
2. Ex02_PacakgeUse.java에서 08번줄의 주석을 풀고 09번 줄을 주석 처리하여 컴파일 하면 오류가 발생한다.
3. Apple 클래스는 같은 폴더에 있으므로 클래스 패스를 따로 지정해주지 않아도 된다.
4. Apple.java 클래스를 com.study 패키지로 옮긴 후에 Ex02_PackagerUse.java 파일을 컴파일 하면 오류가 발생한다.
* */