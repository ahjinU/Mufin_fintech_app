'use client';

import { ComplexInput, Input, InfoShow } from '@/components';
import { commaNum } from '@/utils/commaNum';
import { nullAndNumberRegExp } from '@/utils/regExp';

interface StockBuySellType {
  mode: 'BUY' | 'SELL';
  handlePrice: React.Dispatch<React.SetStateAction<number | undefined>>;
  handleQuantity: React.Dispatch<React.SetStateAction<number | undefined>>;
  totalPrice: number | undefined;
  price: number | undefined;
  quantity: number | undefined;
  upperPrice: number;
  lowerPrice: number;
}

export function StockBuySell({
  mode,
  price,
  quantity,
  handlePrice,
  handleQuantity,
  totalPrice,
  upperPrice,
  lowerPrice,
}: StockBuySellType) {
  return (
    <section className="w-full bg-custom-light-gray flex flex-col gap-[1rem] p-[2rem] rounded-[2rem]">
      <ComplexInput
        isMsg={
          price && (price > upperPrice || price < lowerPrice) ? true : false
        }
        label={`${mode === 'BUY' ? '구매' : '판매'}할 가격`}
        mode="ERROR"
        message={`상한가보다 높거나 하한가보다 낮은 가격으로 ${
          mode === 'BUY' ? '구매' : '판매'
        }할 수 없어요.`}
      >
        <Input
          type="tel"
          placeholder="단가를 입력해주세요."
          value={price}
          setValue={handlePrice}
          regExp={nullAndNumberRegExp}
        />
      </ComplexInput>

      <ComplexInput
        isMsg
        label={`${mode === 'BUY' ? '구매' : '판매'}할 수량`}
        mode="INFORM"
      >
        <Input
          type="tel"
          placeholder="수량을 입력해주세요."
          value={quantity}
          setValue={handleQuantity}
          regExp={nullAndNumberRegExp}
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
