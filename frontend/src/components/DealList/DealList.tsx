'use client';

import React from 'react';
import { Input } from '..';
import { commaNum } from '@/utils/commaNum';

interface DealType {
  item: string;
  cnt: number;
  price: number;
  total: number;
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
      {mode === 'READONLY' ? (
        <>
          {deals?.map(({ item, cnt, total }, index) => {
            return (
              <React.Fragment key={`item-${index}`}>
                <div className="col-span-4 text-[1.6rem] font-normal pl-[0.3rem] text-custom-black">
                  <p>{item}</p>
                </div>
                <div className="text-[1.6rem] font-normal text-center text-custom-black">
                  <p>{cnt}</p>
                </div>
                <div className="col-span-2 text-[1.6rem] font-normal text-right pr-[0.5rem] text-custom-black">
                  <p>{commaNum(total)}원</p>
                </div>
              </React.Fragment>
            );
          })}
        </>
      ) : (
        deals.map(({ item, cnt, total }) => {
          return (
            <>
              <div className="col-span-4 text-[1.6rem] font-normal  flex items-center max-h-[3.5rem]">
                <Input
                  reset={false}
                  width="w-[99%]"
                  height="h-[50%]"
                  value={item}
                />
              </div>
              <div className="text-[1.6rem] font-normal text-center flex items-center max-h-[3.5rem]">
                <Input
                  reset={false}
                  width="w-[97%]"
                  height="h-[50%]"
                  value={cnt}
                />
              </div>
              <div className="col-span-2 text-[1.6rem] font-normal flex items-center justify-end max-h-[3.5rem]">
                <Input
                  reset={false}
                  width="w-[100%]"
                  height="h-[50%]"
                  value={total}
                />
                원
              </div>
            </>
          );
        })
      )}
    </div>
  );
}
