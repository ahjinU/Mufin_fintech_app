'use client';

import { ComplexInput, Input, InfoShow } from '@/components';
import { commaNum } from '@/utils/commaNum';

interface StockBuySellType {
  mode: 'BUY' | 'SELL';
  handlePrice: React.Dispatch<React.SetStateAction<number>>;
  handleQuantity: React.Dispatch<React.SetStateAction<number>>;
  totalPrice: number;
}

export function StockBuySell({
  mode,
  handlePrice,
  handleQuantity,
  totalPrice,
}: StockBuySellType) {
  return (
    <section className="w-full bg-custom-light-gray flex flex-col gap-[1rem] p-[2rem] rounded-[2rem]">
      <ComplexInput
        isMsg
        label={`${mode === 'BUY' ? '구매' : '판매'}할 가격`}
        mode="INFORM"
      >
        <Input placeholder="단가를 입력해주세요." setValue={handlePrice} />
      </ComplexInput>

      <ComplexInput
        isMsg
        label={`${mode === 'BUY' ? '구매' : '판매'}할 수량`}
        mode="INFORM"
      >
        <Input placeholder="수량을 입력해주세요." setValue={handleQuantity} />
      </ComplexInput>

      <ComplexInput
        isMsg
        label="총 가격"
        mode="ERROR"
        message={`${
          mode === 'BUY' ? '구매' : '판매'
        }할 가격과 수량을 모두 입력해주세요!`}
      >
        <InfoShow text={`${commaNum(totalPrice)} 초코칩`} copyIcon={false} />
      </ComplexInput>
    </section>
  );
}
