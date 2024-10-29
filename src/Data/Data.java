package src.Data;

public record Data(String country,String series,float yr2004,float yr2005,float yr2006,float yr2007,float yr2008,float yr2009,float yr2010,float yr2011,float yr2012,float yr2013,float yr2014,float yr2015,float yr2016,float yr2017,float yr2018,float yr2019,float yr2020,float yr2021,float yr2022,float yr2023){
    public Object[] toObjectArray() {
        return new Object[]{country,series,yr2004,yr2005,yr2006,yr2007,yr2008,yr2009,yr2010,yr2011,yr2012,yr2013,yr2014,yr2015,yr2016,yr2017,yr2018,yr2019,yr2020,yr2021,yr2022,yr2023};
    }
}
