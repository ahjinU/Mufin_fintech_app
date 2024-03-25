import { ComplexInput, Input } from '@/components';

export function StockBuySell({ mode }: { mode: 'BUY' | 'SELL' }) {
  return (
    <section className="w-full bg-custom-light-gray flex flex-col gap-[1rem] p-[2rem] rounded-[2rem]">
      <ComplexInput
        isMsg
        label={`${mode === 'BUY' ? '구매' : '판매'}할 가격`}
        mode="INFORM"
      >
        <Input placeholder="단가를 입력해주세요." />
      </ComplexInput>

      <ComplexInput
        isMsg
        label={`${mode === 'BUY' ? '구매' : '판매'}할 수량`}
        mode="INFORM"
      >
        <Input placeholder="수량을 입력해주세요." />
      </ComplexInput>

      <ComplexInput
        isMsg
        label="가격"
        mode="ERROR"
        message={`${
          mode === 'BUY' ? '구매' : '판매'
        }할 가격과 수량을 모두 입력해주세요!`}
      >
        <Input placeholder="0 초코칩" reset={false} disabled={true} />
      </ComplexInput>
    </section>
  );
}
