package com.emlakprojesi.service;

import java.math.BigDecimal;

public class DashboardSummary {

    private final long propertyCount;
    private final long personCount;
    private final long saleCount;
    private final long rentCount;
    private final long soldCount;
    private final long rentedCount;
    private final long dealCount;
    private final BigDecimal saleTotal;
    private final BigDecimal rentTotal;

    public DashboardSummary(long propertyCount, long personCount, long saleCount, long rentCount,
                            long soldCount, long rentedCount, long dealCount,
                            BigDecimal saleTotal, BigDecimal rentTotal) {
        this.propertyCount = propertyCount;
        this.personCount = personCount;
        this.saleCount = saleCount;
        this.rentCount = rentCount;
        this.soldCount = soldCount;
        this.rentedCount = rentedCount;
        this.dealCount = dealCount;
        this.saleTotal = saleTotal;
        this.rentTotal = rentTotal;
    }

    public long getPropertyCount() {
        return propertyCount;
    }

    public long getPersonCount() {
        return personCount;
    }

    public long getSaleCount() {
        return saleCount;
    }

    public long getRentCount() {
        return rentCount;
    }

    public long getSoldCount() {
        return soldCount;
    }

    public long getRentedCount() {
        return rentedCount;
    }

    public long getDealCount() {
        return dealCount;
    }

    public BigDecimal getSaleTotal() {
        return saleTotal;
    }

    public BigDecimal getRentTotal() {
        return rentTotal;
    }
}
