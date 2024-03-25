import { MoneyShow } from '@/components';

export function StockCall() {
  return (
    <section className="flex flex-col gap-[1rem]">
      <h3 className="custom-semibold-text">호가</h3>

      <MoneyShow
        mode="DIVIDED_GRAY"
        money={['83', '43']}
        moneyColor={['text-custom-blue', 'text-custom-red']}
        text={['판매대기', '구매대기']}
        unit="주"
      />

      <table className="table table-fixed border-separate border-spacing-y-[0.5rem]">
        <thead className="custom-medium-text text-center">
          <tr>
            <th>판매 호가량</th>
            <th></th>
            <th>구매 호가량</th>
          </tr>
        </thead>
        <tbody className="custom-medium-text text-center">
          <tr>
            <td className="text-custom-black flex justify-end">
              <div
                className={`w-2/5 bg-custom-blue-with-opacity rounded-l-[0.8rem]`}
              >
                10
              </div>
            </td>
            <td className="text-custom-red">291,000</td>
            <td className="text-custom-black"></td>
          </tr>
          <tr>
            <td className="text-custom-black flex justify-end">
              <div
                className={`w-1/5 bg-custom-blue-with-opacity rounded-l-[0.8rem]`}
              >
                1
              </div>
            </td>
            <td className="text-custom-black">278,000</td>
            <td className="text-custom-black"></td>
          </tr>
          <tr>
            <td className="text-custom-black flex justify-end">
              <div
                className={`w-3/5 bg-custom-blue-with-opacity rounded-l-[0.8rem]`}
              >
                32
              </div>
            </td>
            <td className="text-custom-black">264,500</td>
            <td className="text-custom-black"></td>
          </tr>
          <tr>
            <td className="text-custom-black flex justify-end">
              <div
                className={`w-2/5 bg-custom-blue-with-opacity rounded-l-[0.8rem]`}
              >
                10
              </div>
            </td>
            <td className="text-custom-black">263,000</td>
            <td className="text-custom-black"></td>
          </tr>
          <tr>
            <td className="text-custom-black"></td>
            <td className="text-custom-black">262,800</td>
            <td className="text-custom-black">
              <div
                className={`w-2/5 bg-custom-red-with-opacity rounded-r-[0.8rem]`}
              >
                10
              </div>
            </td>
          </tr>
          <tr>
            <td className="text-custom-black"></td>
            <td className="text-custom-black">261,000</td>
            <td className="text-custom-black">
              <div
                className={`w-5/5 bg-custom-red-with-opacity rounded-r-[0.8rem]`}
              >
                50
              </div>
            </td>
          </tr>
          <tr>
            <td className="text-custom-black"></td>
            <td className="text-custom-black">255,000</td>
            <td className="text-custom-black">
              <div
                className={`w-3/5 bg-custom-red-with-opacity rounded-r-[0.8rem]`}
              >
                32
              </div>
            </td>
          </tr>
          <tr>
            <td className="text-custom-black"></td>
            <td className="text-custom-blue">244,000</td>
            <td className="text-custom-black">
              <div
                className={`w-2/5 bg-custom-red-with-opacity rounded-r-[0.8rem]`}
              >
                10
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </section>
  );
}
