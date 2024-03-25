import { create } from 'zustand';

const useBookStore = create<Model>((set) => ({
  isMonth: true,
  updateIsMonth: () => set((state) => ({ isMonth: !state.isMonth })),

  currentMonth: new Date(),
  updateCurrentMonth: (month) => set(() => ({ currentMonth: month })),
}));

export interface Model {
  isMonth: boolean;
  updateIsMonth: () => void;

  currentMonth: Date;
  updateCurrentMonth: (month: Date) => void;
}

export default useBookStore;
