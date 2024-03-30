import { create } from 'zustand';
import { MyParkingType, MyStockType, RankType } from '../_types';
import { persist } from 'zustand/middleware';

const useStockStore = create(
  persist<Model>(
    (set) => ({
      ranks: [],
      updateRanks: (ranks) => set(() => ({ ranks })),

      myRank: {} as RankType,
      updateMyRank: (myRank) => set(() => ({ myRank })),

      MyParking: {} as MyParkingType,
      updateMyParking: (MyParking) => set(() => ({ MyParking })),

      myStock: {} as MyStockType,
      updateMyStock: (myStock) => set(() => ({ myStock })),
    }),
    {
      name: 'stockStore',
    },
  ),
);

interface Model {
  ranks: RankType[];
  updateRanks: (ranks: RankType[]) => void;

  myRank: RankType;
  updateMyRank: (myRank: RankType) => void;

  myStock: MyStockType;
  updateMyStock: (myStock: MyStockType) => void;

  MyParking: MyParkingType;
  updateMyParking: (MyParking: MyParkingType) => void;
}

export default useStockStore;
