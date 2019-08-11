package org.mybot.resubmit.ex;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * @author lijing
 * @ClassName ResubmitException
 * @Description 数据重复提交校验异常
 * @date 2019/08/11 12:05
 **/
public class ResubmitException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = 1231239238989L;


    private static String exceptionTemple = "[%s-%s]";


    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;


    public ResubmitException(int code, String msgFormat, Object... args) {
        super(String.format(exceptionTemple, code, String.format(msgFormat, args)));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public ResubmitException(String message) {
        super(message);
    }


    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public ResubmitException newInstance(String msgFormat, Object... args) {
        return new ResubmitException(this.code, msgFormat, args);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.code, this.msg);
    }

}
