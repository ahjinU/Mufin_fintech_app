import { SmallButton } from '@/components';
import { StockInfo } from '../_types';

export default function MyStockInfo({ stock }: { stock: StockInfo }) {
  const { cnt, totalPriceAvg, totalPriceCur, priceAvg, priceCur } = stock;
  return (
    <>
      <div className="grid grid-cols-2 gap-x-[2rem] gap-y-[0rem]">
        <div className="flex flex-row justify-between w-full">
          <p className="custom-light-text text-custom-dark-gray">주식평가</p>
          <p className="custom-medium-text  text-custom-black">
            {totalPriceCur}
          </p>
        </div>
        <div className="flex flex-row justify-between w-full">
          <p className="custom-light-text text-custom-dark-gray">평균단가</p>
          <p className="custom-medium-text  text-custom-black">
            {totalPriceAvg}
          </p>
        </div>
        <div className="flex flex-row justify-between w-full">
          <p className="custom-light-text text-custom-dark-gray">매수 초콜릿</p>
          <p className="custom-medium-text  text-custom-black">
            {cnt * priceAvg}
          </p>
        </div>
        <div className="flex flex-row justify-between w-full">
          <p className="custom-light-text text-custom-dark-gray">현재가</p>
          <p className="custom-medium-text  text-custom-black">{priceCur}</p>
        </div>
      </div>
      <div className="flex flex-row w-full gap-[1rem] justify-center">
        <SmallButton mode={'ACTIVE'} label={'더 사기'} />
        <SmallButton mode={'ACTIVE'} label={'팔기'} />
      </div>
    </>
  );
}
