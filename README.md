# Todotodo (중앙대학교 설계 패턴 강의)

- Java : JDK 1.7
- SpringBoot : 3.1.3
- intelliJ IDEA CE

테이블 정보를 담은 csv (nodes.csv, todo_lists.csv, node_lists.csv, categories.csv, node_array_list.csv)와 각 테이블의 pk를 저장하는 keys.csv를 base 폴더에 위치해야한다. (예시 "todotodo/keys.csv")
src/main/java/com/project/todotodo/TodotodoApplication.java를 통해 웹 서비스를 실행할 수 있다.
"localhost:8080/todolist/year/month/day" : 날짜 별 todolist 모아 보기, 등록하기
"localhost:8080/goal/create" : 카테고리 등록
"localhost:8080/goal/list" : 카테고리 리스트 조회
