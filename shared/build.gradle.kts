import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform) // Compose Multiplatform 플러그인 추가
    alias(libs.plugins.composeCompiler) // Compose Compiler 플러그인 추가

    alias(libs.plugins.mavenPublish) // Maven 게시 기능 추가
    signing // PGP 서명 활성화 (Maven Central 필수)
    // PGP(Pretty Good Privacy) 서명 : 개인이 가진 비밀키로 배포된 라이브러리가 원본임이 증명됨. 개발자는 GPG 도구를 통해 키 쌍을 생성. 공개키를 서버에 업로드
    // 다운르도한 사용자가 공개키로 서명을 확인할 수 있음
    alias(libs.plugins.vanniktechMavenPublish) // Gradle 설정을 간소화시키는 플러그인 (기본적으로 publication, 서명, POM 구성 포함)
}

group = "com.yourssu"
version = "0.0.1"
// Maven 좌표 설정 (groupId:artifactId:version)—Central에 필요한 기본 요소

kotlin {
    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.yourssu.shared"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "sharedKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    jvm("desktop")

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.testExt.junit)
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }
    }
}

mavenPublishing {
    publishToMavenCentral() // Maven Central 배포 타겟 설정
    signAllPublications() // publication 전부 PGP 서명 적용
    coordinates(group.toString(), "calendar-lib", version.toString())


    // Maven Coordinate = groupId:artifactId:version (줄여서 GAV)
    // groupID : 조직이나 프로젝트를 식별하는 고유 네임스페이스
    //      추천 형식: 도메인명 반전 (com.mydomain) 또는 io.github.사용자명
    // artifactId : 같은 groupId 내에서 사용하는 라이브러리 이름. 같은 group 내 라이브러리의 유일 이름
    //      캘린더 유틸이면 calendar-lib 같은 형태
    // version : 해당 artifact의 버전 번호.
    //      예: "1.0.0", "1.0.0-SNAPSHOT" 등.
    //      Maven은 이 버전을 기준으로 사용자 의존성 관리 및 업데이트를 수행

    pom { // POM(Project Object Model) Maven 프로젝트를 구성하고 배포에 필요한 모든 설정과 메타데이터 정의(XML 파일)
        name = "Kanendar Library"
        description = "A Kotlin Multiplatform calendar utility library"
        inceptionYear = "2025"
        url = "https://github.com/yourssu/Kalendar"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "Gael-Android"
                name = "kwak kun"
                email = "gael.urssu@gmail.com"
            }
        }
        scm { // SCM (Source Code Management). 코드 저장소 위치와 접근 방법을 POM에 정의하여, 릴리즈 자동화 도구들이 소스 제어와 태깅 작업을 수행할 수 있게 하는 설정
            // 브라우저에서 프로젝트 링크
            url = "https://github.com/yourssu/Kalendar"
            // 사용자가 읽기 전용으로 clone할 때 사용
            connection = "scm:git:git://github.com/yourssu/Kalendar.git"
            // 릴리즈 자동화 시 SSH 인증이 필요한 쓰기 권한
            developerConnection = "scm:git:git://github.com/yourssu/Kalendar.git"
        }
    }
}