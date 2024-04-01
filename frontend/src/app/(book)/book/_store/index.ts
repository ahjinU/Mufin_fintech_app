import { create } from 'zustand';
import { persist } from 'zustand/middleware';

const useBookStore = create(
  persist<Model>(
    (set) => ({
      isMonth: true,
      updateIsMonth: () => set((state) => ({ isMonth: !state.isMonth })),

      currMonth: new Date(),
      updateCurrentMonth: (month) => set(() => ({ currMonth: month })),

      selectedDate: new Date(),
      updateSelectDate: (date) => set(() => ({ selectedDate: date })),

      currentStartDate: new Date(),
      updateCurrentStartDate: (date) => set(() => ({ currentStartDate: date })),

      currentEndDate: new Date(),
      updateCurrentEndDate: (date) => set(() => ({ currentEndDate: date })),

      selectedTransaction: {
        amount: 0,
        code: '',
        counterpartyName: '',
        date: '',
        memo: null,
        receipts: undefined,
        transactionUuid: '',
      },
      updateSelectedTransaction: (transaction) =>
        set(() => ({ selectedTransaction: transaction })),

      children: [],
      updateChildren: (children) => set(() => ({ children: children })),

      curChild: {
        name: '',
        childUuid: '',
        index: 0,
      },
      updateCurChild: (curChild) => set(() => ({ curChild: curChild })),
    }),
    {
      name: 'bookStore',
    },
  ),
);

export interface Model {
  isMonth: boolean;
  updateIsMonth: () => void;

  currMonth: Date;
  updateCurrentMonth: (month: Date) => void;

  selectedDate: Date;
  updateSelectDate: (date: Date) => void;

  currentStartDate: Date;
  updateCurrentStartDate: (date: Date) => void;

  currentEndDate: Date;
  updateCurrentEndDate: (date: Date) => void;

  selectedTransaction: TransactionType;
  updateSelectedTransaction: (transaction: TransactionType) => void;

  children: childType[];
  updateChildren: (children: childType[]) => void;

  curChild: childType;
  updateCurChild: (curChild: childType) => void;
}

export default useBookStore;
