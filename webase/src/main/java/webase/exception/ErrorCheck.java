package webase.exception;

import com.bin.webase.domain.container.DomainRegistry;

import java.util.Collection;

public class ErrorCheck {

    public static void check(boolean result, ErrorCode errorCode, String msg) {
        if (!result) {
            throw new ApplicationException(errorCode, msg);
        }
    }

    public static void check(boolean result, ErrorCode errorCode) {
        if (!result) {
            throw new ApplicationException(errorCode);
        }
    }

    public static void checkArgument(boolean result, String msg) {
        if (!result) {
            throw new ApplicationException(ErrorCode.ArgumentsIncorrect, msg);
        }
    }

    public static void checkNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new ApplicationException(ErrorCode.NullPointerException, msg);
        }
    }

    public static void checkNotNull(Object obj, ErrorCode errorCode) {
        if (obj == null) {
            throw new ApplicationException(errorCode);
        }
    }

    public static void checkCollectionNotEmpty(Collection collection, String msg) {
        if (collection == null || collection.size() == 0) {
            throw new ApplicationException(ErrorCode.NullPointerException, msg);
        }
    }

    public static void checkServerBusy(boolean result) {
        if (!result) {
            throw new ApplicationException(ErrorCode.ServerBusy);
        }
    }

    public static void checkDuplicateOperate(String key) {
        if (DomainRegistry.getCacheBean().hasKey(key)) {
            throw new ApplicationException(ErrorCode.QuickOperate);
        } else {
            DomainRegistry.getCacheBean().set(key, "", 2);
        }
    }
}
