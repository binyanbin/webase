package webase.domain.dto;

import com.bin.webase.domain.unitwork.CommitResult;
import com.bin.webase.exception.ErrorCode;

import java.math.BigDecimal;
import java.util.List;

public class DTOUtils {
    public static NoResultDTO success() {
        return new NoResultDTO(0);
    }

    public static DTO success(boolean result) {
        return new DTO<>(result);
    }

    public static DTO success(long result) {
        return new DTO<>(result);
    }

    public static DTO success(String result) {
        return new DTO<>(result);
    }

    public static DTO success(List<String> result) {
        return new DTO<>(result);
    }

    public static DTO success(BigDecimal result) {
        return new DTO<>(result);
    }

    public static NoResultDTO response(CommitResult commitResult) {
        if (commitResult.isSuccess()) {
            return success();
        } else {
            if (commitResult.getMsg() == null || commitResult.getMsg().length() == 0) {
                return fail(ErrorCode.UnitWorkCommitFail);
            } else {
                return fail(ErrorCode.UnitWorkCommitFail, commitResult.getMsg());
            }
        }
    }

    public static ErrorDTO fail(ErrorCode errorCode, String msg) {
        return new ErrorDTO(errorCode.getCode(), msg);
    }

    public static ErrorDTO fail(ErrorCode errorCode) {
        return new ErrorDTO(errorCode.getCode(), errorCode.getReasoning());
    }

    public static <T> DTO<T> success(T data) {
        return new DTO<T>(data);
    }
}
