package nextstep.subway.common.exception;

public class PathSameException extends RuntimeException {
    private static final String NOT_FIND_SOURCE_TARGET_SAME = "출발역과 도착역이 같은 경우 경로 조회 할 수 없습니다.";

    public PathSameException() {
        super(NOT_FIND_SOURCE_TARGET_SAME);
    }
}
