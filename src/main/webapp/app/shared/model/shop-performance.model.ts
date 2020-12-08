export interface IShopPerformance {
  id?: number;
  shopName?: string;
  primeraConsulta?: string;
  primeraConsultaLeave?: string;
  nuevaConsulta?: string;
  todayPerformance?: string;
  createTime?: string;
  del?: number;
}

export class ShopPerformance implements IShopPerformance {
  constructor(
    public id?: number,
    public shopName?: string,
    public primeraConsulta?: string,
    public primeraConsultaLeave?: string,
    public nuevaConsulta?: string,
    public todayPerformance?: string,
    public createTime?: string,
    public del?: number
  ) {}
}
