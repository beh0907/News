# <div align=center>ChatApp</div>

[News API](https://newsapi.org/)를 활용한 kotlin 언어 기반의 뉴스 기사 어플리케이션입니다.

# 특징

* 최신 뉴스 기사 목록 제공
* 뉴스 기사 검색
* 뉴스 기사 북마크 및 관련 영상 제공

# 기술스택 및 라이브러리

* 최소 SDK 26 / 타겟 SDK 34
* kotlin 언어 기반, 비동기 처리를 위한 coroutine + Flow
* 종속성 주입을 위한 [Dagger Hilt](https://dagger.dev/hilt/)
* JetPack
  * Compose - Android의 현대적인 선언적 UI 툴킷으로, Kotlin 기반의 UI 개발을 통해 효율적이고 유연한 사용자 인터페이스 구축합니다.
  * LifeCycle - Android의 수명 주기를 관찰하고 수명 주기 변경에 따라 UI 상태를 처리합니다.
  * ViewModel - UI와 DATA 관련된 처리 로직을 분리합니다.
  * ViewBinding - View(Compose)와 코드(kotlin)간의 상호작용을 원활하게 처리합니다.
  * Paging3 - 무한 스크롤 목록을 처리하고 관리합니다. (API 페이징 처리)
  * Navigation - [Compose Navigation](https://developer.android.com/develop/ui/compose/navigation?hl=ko)을 활용해 화면 전환시 Type-Safe한 인자를 전달하고 Notification과 연계하여 채팅방의 Deep Link를 구현합니다.
  * DataStore - SharedPreferences의 한계점을 개선한 라이브러리로 앱 최초 실행여부를 저장해 온보딩 화면 출력 여부를 관리합니다.
  * Room - 로컬 DB인 SQLite를 추상화한 라이브러리로 뉴스 북마크 목록을 관리합니다.
* Architecture
  * MVVM 패턴 적용 - Model + View + ViewModel
  * MVI 패턴 적용 - Model + View + Intent
  * Repository 패턴 적용 - Data + Domain + Presentation Layer 클린 아키텍처
* [Retrofit2](https://github.com/square/retrofit) - News API로부터 뉴스 기사를 목록을 요청하고 처리합니다.
* [Coil](https://coil-kt.github.io/coil/README-ko/) - Coroutine기반으로 효율적인 비동기 이미지를 로드하고 적용합니다.

# 스크린샷

|온보딩 화면|메인 화면|검색 화면|
|---|---|---|
| ![온보딩 화면](https://github.com/user-attachments/assets/d07a8060-499b-46bd-9833-5a730040160f) | ![메인화면](https://github.com/user-attachments/assets/e491fe17-0b0c-43e9-818c-5ea14ff06059) | ![검색화면](https://github.com/user-attachments/assets/a78d9f93-8c31-40a9-ba34-6d114adc8438) |


|북마크 화면|뉴스 기사 화면|
|---|---|
| ![북마크화면](https://github.com/user-attachments/assets/ce92d20b-aba5-4613-a337-bb5523b56291) |![뉴스 기사 화면](https://github.com/user-attachments/assets/95de6dbf-0c26-482f-b204-8eafcab9682d)|
