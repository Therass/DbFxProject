package util;

import parameters.DbParameters;

public class IsSql {

    public IsSql(String isSQL) {
        if (isSQL.equals("SQL")) {
            DbParameters.getInstance().setIS_SQL(true);
        } else {
            DbParameters.getInstance().setIS_SQL(false);
        }


    }
}

