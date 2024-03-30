import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import { LoanApplyType } from '../_types/type';

const useLoanApplyStore = create(
  persist<Model>(
    (set) => ({
      apply: {
        reason: '',
        amount: 0,
        paymentTotalCnt: 0,
        paymentDate: 0,
        conversation: '',
        penalty: '',
      },
      updateApply: (apply) => set(() => ({ apply })),
      addConversation: (conversation) =>
        set((state) => ({
          apply: { ...state.apply, conversation: conversation },
        })),
    }),
    {
      name: 'stockStore',
    },
  ),
);

interface Model {
  apply: LoanApplyType;
  updateApply: (apply: LoanApplyType) => void;
  addConversation: (conversation: string) => void;
}

export default useLoanApplyStore;
