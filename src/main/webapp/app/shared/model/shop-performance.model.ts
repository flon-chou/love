export interface IShopPerformance {
  id?: number;
  shopName?: string;
  primeraConsulta?: number;
  primeraConsultaLeave?: number;
  nuevaConsulta?: number;
  todayPerformance?: number;
  totalPerformance?: number;
  createTime?: string;
  del?: number;
}

export class ShopPerformance implements IShopPerformance {
  constructor(
    public id?: number,
    public shopName?: string,
    public primeraConsulta?: number,
    public primeraConsultaLeave?: number,
    public nuevaConsulta?: number,
    public todayPerformance?: number,
    public totalPerformance?: number,
    public createTime?: string,
    public del?: number
  ) {}
}
