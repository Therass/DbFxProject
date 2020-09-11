package util;

import parameters.DbParameters;

public class IsSql {

    public IsSql(String isSQL) {
        if (isSQL.equals("SQL")) {
            DbParameters.IS_SQL = true;
        } else {
            DbParameters.IS_SQL = false;
        }


    }
}

