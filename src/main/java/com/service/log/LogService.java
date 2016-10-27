package com.service.log;

import com.dao.LogDao;
import com.entity.Log;
import com.utils.annotation.LogManage;
import com.utils.annotation.LogModel;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Service
@Transactional(rollbackFor = {RuntimeException.class})
public class LogService {

    @Autowired
    private LogDao logDao;

    public void save() {
        LogManage lm = LogManage.getInstance();
        Vector<LogModel> vector = lm.getLog();
        Vector<Log> v = new Vector<Log>();
        for (LogModel logModel : vector) {
            Log ul = new Log();
            ul.setCreateDate(logModel.getCreateDate());
            ul.setLoginIp(logModel.getLoginIp());
            ul.setOperationDesc(logModel.getOperationDesc());
            String operation = logModel.getOperation();
            ul.setOperation(operation);
            String[] o = operation.split("/");
            for (String module : o) {
                if (!module.equals("")) {
                    ul.setModule(module);
                    break;
                }
            }
            String paras = logModel.getParas();
            if (paras != null) {
                ul.setParas(paras);
            }
            ul.setSpendTime(logModel.getSpendTime());
            ul.setStatus(logModel.getStatus());
            if (logModel.getUserId() != null) {
                ul.setUserId(logModel.getUserId());
            }
            if (logModel.getUserName() != null) {
                ul.setUserName(logModel.getUserName());
            }
            ul.setLevel(logModel.getLevel());
            ul.setError(logModel.getError());
            ul.setMessage(logModel.getMessage());
            v.add(ul);
        }
        logDao.batchSave(v, 100);
    }

    public List<Log> getLog(Long userId, Integer first, Integer max) {
        return logDao.getLogByUserId(userId, first, max);
    }

    public Integer getLog(Long userId) {
        return logDao.getLogByUserId(userId);
    }

    public List<Log> getAllLog(Integer first, Integer max) {
        return logDao.getAllLogByPage(first, max);
    }

    public long getCount() {
        return logDao.findAll().size();
    }

}
