package com.lamport.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.FreeListenBookMapper;
import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.service.StatisticsService;
import com.lamport.admin.vo.QIDAndPage;
import com.lamport.admin.vo.StatisticsQueryResult;

/**
 * implements StatisticsService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class StatisticsServiceBean implements StatisticsService {
	
	@Autowired
	SorderMapper sorderMapper;
	@Autowired
	FreeListenBookMapper freeListenBookMapper;

	@Override
	public List<StatisticsQueryResult> selectMonthCountSorderAndBookByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		System.out.println("..........SorderServiceBean..........selectCountSorderAndBookByQIDAndPage()..........");
		
		List<StatisticsQueryResult> statisticsQueryResults = new ArrayList<StatisticsQueryResult>();
		List<StatisticsQueryResult> sorderQueryResults = null;
		List<StatisticsQueryResult> bookQueryResults = null;
		
		List<String> months = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		//获取当月的月份
		calendar.add(Calendar.MONTH, 0);
		String month = df.format(calendar.getTime());
		months.add(month);
		//循环获取前qidAndPage.getLimit()-1个月的月份
		for(int i=1; i<qidAndPage.getLimit(); i++){
			calendar.add(Calendar.MONTH, -1);
			month = df.format(calendar.getTime());
			months.add(month);
		}
		sorderQueryResults = sorderMapper.selectMonthCountSorderByQIDAndPage(qidAndPage);
		bookQueryResults = freeListenBookMapper.selectMonthCountBookByQIDAndPage(qidAndPage);
		/*##########*/
//		System.out.println("\nmonths:");
//		for(int i=0; i<months.size(); i++){
//			System.out.println(months.get(i));
//		}
//		System.out.println("\n\n");
		/*##########*/
		//校验月份是否匹配，并将查询到的信息放入statisticsQueryResults
//		System.out.println("qidAndPage.getLimit() = " + qidAndPage.getLimit());/*##########*/
//		System.out.println("months.size() = " + months.size());/*##########*/
		for(int i=0; i<qidAndPage.getLimit(); i++){
			StatisticsQueryResult queryResult = new StatisticsQueryResult();
			queryResult.setQuerymonth(months.get(i));
			int countsorder = 0;
			int countbook = 0;
			for(int j=0; j<sorderQueryResults.size(); j++){
				StatisticsQueryResult sorderResult = sorderQueryResults.get(j);
				if(months.get(i).equals(sorderResult.getQuerymonth())){
					countsorder = sorderResult.getCountsorder();
					break;
				}
			}
			for(int j=0; j<bookQueryResults.size(); j++){
				StatisticsQueryResult bookResult = bookQueryResults.get(j);
				if(months.get(i).equals(bookResult.getQuerymonth())){
					countbook = bookResult.getCountbook();
					break;
				}
			}
			queryResult.setCountbook(countbook);
			queryResult.setCountsorder(countsorder);
			statisticsQueryResults.add(queryResult);
		}
		
		return statisticsQueryResults;
	}

	@Override
	public List<StatisticsQueryResult> selectDayCountSorderActualByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		System.out.println("..........SorderServiceBean..........selectCountSorderActualByQIDAndPage()..........");
		
		List<StatisticsQueryResult> statisticsQueryResults = new ArrayList<StatisticsQueryResult>();
		List<StatisticsQueryResult> actualQueryResults = null;
		
		List<String> days = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//获取当天的日期
		calendar.add(Calendar.DATE, 0);
		String day = df.format(calendar.getTime());
		days.add(day);
		//循环获取前qidAndPage.getLimit()-1天的日期
		for(int i=1; i<qidAndPage.getLimit(); i++){
			calendar.add(Calendar.DATE, -1);
			day = df.format(calendar.getTime());
			days.add(day);
		}
		actualQueryResults = sorderMapper.selectDayCountSorderActualByQIDAndPage(qidAndPage);
		/*##########*/
//		for(int i=0; i<actualQueryResults.size(); i++){
//			StatisticsQueryResult actualResult = actualQueryResults.get(i);
//			System.out.println(actualResult.getQuerydate() + "\t" + actualResult.getCountactual());
//		}
//		System.out.println("\n\n");
		/*##########*/
		//校验日期是否匹配，并将查询到的信息放入statisticsQueryResults
		for(int i=0; i<qidAndPage.getLimit(); i++){
			StatisticsQueryResult queryResult = new StatisticsQueryResult();
			queryResult.setQuerydate(days.get(i));
			double countactual = 0.0;
			for(int j=0; j<actualQueryResults.size(); j++){
				StatisticsQueryResult actualResult = actualQueryResults.get(j);
				if(days.get(i).equals(actualResult.getQuerydate())){
					countactual = actualResult.getCountactual();
//					System.out.println(days.get(i) + "\t" + countactual);/*##########*/
					break;
				}
			}
			queryResult.setCountactual(countactual);
			statisticsQueryResults.add(queryResult);
		}

		return statisticsQueryResults;
	}

}
