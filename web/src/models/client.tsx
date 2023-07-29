export type Client = {
  id?: string;
  createdAt?: string;
  updatedAt?: string;
  phone?: string;
  individual?: {
    ir: string;
    birthday?: Date;
    name: string;
  } | null;
  legal?: {
    cnpj: string;
    corporateName: string;
    trade: string;
  } | null;
};
