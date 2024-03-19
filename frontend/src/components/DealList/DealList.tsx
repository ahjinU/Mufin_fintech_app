'use client';

import { Input } from '..';

interface DealType {
  name: string | number;
  count: string | number;
  totalPrice: string | number;
}

interface DealListProps {
  mode: 'MODIFY' | 'READONLY';
  deals: DealType[];
}

export default function DealList({ mode, deals, ...props }: DealListProps) {
  return (
    <div className="grid grid-cols-7 w-full">
      <div className="col-span-4 custom-medium-text text-custom-dark-gray flex items-center  border-b mb-[0.3rem]">
        거래내역명
      </div>
      <div className="custom-medium-text text-custom-dark-gray flex items-center justify-center border-b mb-[0.3rem]">
        수량
      </div>
      <div className="col-span-2 custom-medium-text text-custom-dark-gray flex items-center justify-end border-b  pr-[0.5rem]  mb-[0.3rem]">
        지출금액
      </div>
      {mode === 'READONLY'
        ? deals.map(({ name, count, totalPrice }) => {
            return (
              <>
                <div className="col-span-4 text-[1.6rem] font-normal pl-[0.3rem]">
                  <p>{name}</p>
                </div>
                <div className="text-[1.6rem] font-normal text-center">
                  <p>{count}</p>
                </div>
                <div className="col-span-2 text-[1.6rem] font-normal text-right pr-[0.5rem]">
                  <p>{totalPrice}원</p>
                </div>
              </>
            );
          })
        : deals.map(({ name, count, totalPrice }) => {
            return (
              <>
                <div className="col-span-4 text-[1.6rem] font-normal  flex items-center max-h-[3.5rem]">
                  <Input
                    reset={false}
                    width="w-[99%]"
                    height="h-[50%]"
                    value={name}
                  />
                </div>
                <div className="text-[1.6rem] font-normal text-center flex items-center max-h-[3.5rem]">
                  <Input
                    reset={false}
                    width="w-[97%]"
                    height="h-[50%]"
                    value={count}
                  />
                </div>
                <div className="col-span-2 text-[1.6rem] font-normal flex items-center justify-end max-h-[3.5rem]">
                  <Input
                    reset={false}
                    width="w-[100%]"
                    height="h-[50%]"
                    value={totalPrice}
                  />
                  원
                </div>
              </>
            );
          })}
    </div>
  );
}
