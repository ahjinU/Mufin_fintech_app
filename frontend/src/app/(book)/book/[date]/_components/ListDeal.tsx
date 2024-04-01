'use client';

import React from 'react';

interface DealType {
  item: string;
  cnt: number;
  price: number;
  total: number;
}

interface DealListProps {
  deals: DealType[];
}

export default function ListDeal({ deals }: DealListProps) {
  return (
    <div className="grid grid-cols-7 w-full bg-custom-white p-[0.5rem] rounded-[0.8rem] mt-[0.5rem]">
      <div className="col-span-4 text-[1rem] text-custom-medium-gray flex items-center  border-b mb-[0.3rem]">
        거래내역명
      </div>
      <div className="text-[1rem]  text-custom-medium-gray flex items-center justify-center border-b mb-[0.3rem]">
        수량
      </div>
      <div className="col-span-2 text-[1rem] text-custom-medium-gray flex items-center justify-end border-b  pr-[0.5rem]  mb-[0.3rem]">
        지출금액
      </div>
      {deals.map(({ item, cnt, total }, index) => {
        return (
          <React.Fragment key={`item-deal-${index}`}>
            <div className="col-span-4 text-[1.2rem] font-normal pl-[0.3rem] text-custom-dark-gray">
              <p>{item}</p>
            </div>
            <div className="text-[1.2rem] font-normal text-center text-custom-dark-gray">
              <p>{cnt}</p>
            </div>
            <div className="col-span-2 text-[1.2rem] font-normal text-right pr-[0.5rem] text-custom-dark-gray">
              <p>{total}원</p>
            </div>
          </React.Fragment>
        );
      })}
    </div>
  );
}
