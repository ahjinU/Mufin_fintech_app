'use client';

import { ComplexInput, Input, InfoShow } from '@/components';
import { commaNum } from '@/utils/commaNum';

interface StockBuySellType {
  mode: 'BUY' | 'SELL';
  handlePrice: React.Dispatch<React.SetStateAction<number | undefined>>;
  handleQuantity: React.Dispatch<React.SetStateAction<number | undefined>>;
  totalPrice: number | undefined;
  price: number | undefined;
  quantity: number | undefined;
}

export function StockBuySell({
  mode,
  price,
  quantity,
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
        <Input
          placeholder="단가를 입력해주세요."
          value={price}
          setValue={handlePrice}
          isNumber={true}
        />
      </ComplexInput>

      <ComplexInput
        isMsg
        label={`${mode === 'BUY' ? '구매' : '판매'}할 수량`}
        mode="INFORM"
      >
        <Input
          placeholder="수량을 입력해주세요."
          value={quantity}
          setValue={handleQuantity}
          isNumber={true}
        />
      </ComplexInput>

      <ComplexInput
        isMsg={!price || !quantity}
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
