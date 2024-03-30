import { create } from 'zustand';

const useLoanRepayStore = create<Model>((set) => ({
  paymentCnt: 1,
  updatePaymentCnt: (paymentCnt: number) => set(() => ({ paymentCnt })),
}));

interface Model {
  paymentCnt: number;
  updatePaymentCnt: (paymentCnt: number) => void;
}

export default useLoanRepayStore;
