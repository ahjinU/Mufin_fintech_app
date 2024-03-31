import { create } from 'zustand';
import { persist } from 'zustand/middleware';

interface UserDataType {
  name: string;
  isParent: boolean;
  accountNumber: string;
  balance: number;
  savings: number;
  monthAmounts: number;
  ranking: number;
  chocochip: number;
  totalIncome: number;
  totalIncomePercent: string;
  totalPrice: number;
}

const useUserStore = create(
  persist<Model>(
    (set) => ({
      userData: {
        name: '',
        isParent: false,
        accountNumber: '',
        balance: 0,
        savings: 0,
        monthAmounts: 0,
        ranking: 0,
        chocochip: 0,
        totalIncome: 0,
        totalIncomePercent: '',
        totalPrice: 0,
      } as UserDataType,

      setUserData: (userData: UserDataType) => set(() => ({ userData })),
    }),
    {
      name: 'userStore',
    },
  ),
);

interface Model {
  userData: UserDataType;
  setUserData: (userData: UserDataType) => void;
}

export default useUserStore;
